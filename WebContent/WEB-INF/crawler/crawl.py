from bs4 import BeautifulSoup as bs4
import urllib.request as request
import re
import os

base_addr = 'https://en.wikibooks.org'
java_filter = '/wiki/Java_Programming'

output_folder = os.path.join(os.path.dirname(__file__), "..", "input")

def write_to_file(link, text, fileid):
	text_file = open(os.path.abspath(os.path.join(output_folder, str(fileid) + '.txt')),'w')
	text_file.write(link)
	text_file.write('\n')
	text_file.write(text)
	text_file.close()

def process_link(link, fileid):
	with request.urlopen(link) as url:
		r = url.read()
	soup = bs4(r, "html.parser")
	content = soup.find(id='content')

	#extract text
	text = re.sub( '\s+', ' ', content.get_text()).strip()
	write_to_file(link, text, fileid)

	#extract links
	links = content.find_all('a', href=True)
	return [link['href']  for link in links if link['href'].startswith(java_filter)]

def main():
	fileid = 100
	visited = []
	fringe = []
	
	fringe.append(java_filter)
	while fringe:
		link = fringe.pop()
		if link in visited:
			continue
		visited.append(link)
		fileid += 1
		links = process_link(base_addr + link, fileid)
		links = [link for link in links if link not in visited]
		fringe.extend(links)

if __name__ == '__main__':
	main()
