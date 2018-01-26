package com.aw_assg3;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.RAMDirectory;

import java.io.*;
import java.util.ArrayList;


public class Lucene {
	private static RAMDirectory indexDir;
	
	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f); // recurse
			} else if (f.getName().endsWith(".txt")) {
				// call indexFile to add the title of the txt file to your index (you can also index html)
				indexFile(writer, f);
			}
		}
	}
	private static void indexFile(IndexWriter writer, File f) throws IOException {
		System.out.println("Indexing " + f.getName());
		Document doc = new Document();
		doc.add(new TextField("filename", f.getName(), TextField.Store.YES));
		
		//open each file to index the content
		try{
			FileInputStream is = new FileInputStream(f);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    StringBuffer stringBuffer = new StringBuffer();
		    String line = null;
		    if((line = reader.readLine())!=null) {
		    		stringBuffer.append(line).append("\n");
		        	doc.add(new TextField("link", line, TextField.Store.YES));
		    }
		    while((line = reader.readLine())!=null){
		    		stringBuffer.append(line).append("\n");
		    }
		    reader.close();
			doc.add(new TextField("contents", stringBuffer.toString(), TextField.Store.YES));
		}catch (Exception e) {
            System.out.println("something wrong with indexing content of the files");
        }    
		writer.addDocument(doc);
   	}	
	
	public static RAMDirectory getIndexDir(String inputDir) throws IOException, ParseException {
		if(indexDir == null ) {
			File dataDir = new File(inputDir); //my sample file folder path
			// Check whether the directory to be indexed exists
			if (!dataDir.exists() || !dataDir.isDirectory()) {
				throw new IOException(dataDir + " does not exist or is not a directory");
			}
			indexDir = new RAMDirectory();
			
			// Specify the analyzer for tokenizing text.
			StandardAnalyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(indexDir, config);
			
			// call indexDirectory to add to your index
			// the names of the txt files in dataDir
			indexDirectory(writer, dataDir);
			writer.close();
		 }
		return indexDir;
	}
	 
	public static ArrayList<String> search(String querystr) throws IOException, ParseException {
		// Specify the analyzer for tokenizing text.
		StandardAnalyzer analyzer = new StandardAnalyzer();

		Query q = new QueryParser( "contents", analyzer).parse(QueryParser.escape(querystr));
		int hitsPerPage = 10;
		IndexReader reader = null;
		 
		TopScoreDocCollector collector = null;
		IndexSearcher searcher = null;
		reader = DirectoryReader.open(indexDir);
		searcher = new IndexSearcher(reader);
		collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(q, collector);
		 
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		System.out.println("Found " + hits.length + " hits.");
		System.out.println();
		
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d;
			d = searcher.doc(docId);
			result.add(d.get("link"));
		}
		reader.close();
		return result;
	}
}