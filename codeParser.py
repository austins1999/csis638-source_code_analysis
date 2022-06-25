
import sys
import re

def main():
	
	#name = "UndirectedGraph"
	name = "BinarySearchTree"
	
	infile_name = name + ".java"
	outfile_name = name + "_output.pl"
	
	# read in source code from file and close
	infile = open(infile_name, "r")
	file_lines = infile.readlines()
	infile.close()
	
	# open output file and redirect stdout to point to it
	outfile = open(outfile_name, "w")
	sys.stdout = outfile
	
	# begin parsing source code to generate facts
	for i in range(len(file_lines)):
		
		# check for comments
		if "/*" in file_lines[i]:
			print("blockComment("+str(i+1)+", start).")
		elif "*/" in file_lines[i]:
			print("blockComment("+str(i+1)+", end).")
		elif "*" in file_lines[i]:
			continue
		elif "//" in file_lines[i]:
			continue

		# check for loop and conditional structures
		elif re.search(".*for.*{", file_lines[i]) or re.search(".*while.*{", file_lines[i]):
			print("loopStart("+str(i)+", "+file_lines[i].split(" ")[0].split("(")[0].strip("\t")+").")
		elif re.search(".*if.*{", file_lines[i]) or re.search(".*else.*{", file_lines[i]):
			print("conditionalCheck("+str(i)+", "+file_lines[i].split(" ")[0].split("(")[0].strip("\t")+").")
		
		# check for try/catch blocks
		elif "try" in file_lines[i]:
			print("tryCatchBlock("+str(i+1)+", try_start).")
		elif "catch" in file_lines[i]:
			print("tryCatchBlock("+str(i+1)+", catch_end).")
		
		# check for class and function headers
		elif re.search(".*class.*", file_lines[i]):
			print("classHeader("+str(i+1)+", "+file_lines[i].split(" ")[0]+", "+file_lines[i].split(" ")[2].split("<")[0].lower()+").")
		elif (re.search(".*public.*(.*)", file_lines[i]) or re.search(".*private.*(.*)", file_lines[i])) and not re.search(".*;", file_lines[i]):
			returnType = file_lines[i].split(" ")[2].split("(")[0].lower()
			if "{" in returnType:
				print("functionHeader("+str(i+1)+", "+file_lines[i].split(" ")[0].strip("\t")+", class_constructor).")
			else:
				print("functionHeader("+str(i+1)+", "+file_lines[i].split(" ")[0].strip("\t")+", "+file_lines[i].split(" ")[1].lower()+", "+returnType+").")
				
		# check for variable instantiations
		elif re.search(".*int .*=.*;", file_lines[i]):
			print("variableCreated("+str(i+1)+", int, "+file_lines[i].split(" ")[1].lower()+").")
		elif re.search(".*String .*=.*;", file_lines[i]):
			print("variableCreated("+str(i+1)+", string, "+file_lines[i].split(" ")[1].lower()+").")
		elif re.search(".*Boolean .*=.*;", file_lines[i]):
			print("variableCreated("+str(i+1)+", boolean, "+file_lines[i].split(" ")[1].lower()+").")
			
		# check for simple statements
		elif "import" in file_lines[i] or "return" in file_lines[i]:
			print("controlStatement("+str(i+1)+", "+file_lines[i].split(" ")[0].strip("\t")+").")
		elif "System.out.print" in file_lines[i]:
			print("controlStatement("+str(i+1)+", print).")
			
	
	# close output file before termination
	outfile.close()
	
main()
