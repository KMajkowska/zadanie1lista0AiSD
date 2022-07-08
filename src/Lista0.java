
import java.util.Scanner;
import java.io.*;
import java.util.*;


class TextAnalyser {
	ArrayList<String>List;
	ArrayList<String>Words;
	String sword;
	TextAnalyser()
	{
		List = new ArrayList<String>();
		Words = new ArrayList<String>();
		sword = "";
	}
	Boolean LoadFile( String fname )
	{		
		File text = new File(fname);
		String line;
		 try (BufferedReader reader = new BufferedReader(new FileReader(fname))){
			while((line = reader.readLine()) != null)
			{
				List.add(line);
			}
			return true;
		 }catch(IOException e) {
		  return false;
		 }
	}
	Boolean SaveFile( String fname )
	{		
		File text = new File(fname);
		int i=0;
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(fname))){
			 while(i<List.size())
			 {
				 writer.write(List.get(i));
				 writer.write('\n');
				 i++;
			 }
			 return true;
		 }catch(IOException e) {
		  return false;
		 }
	}
	Boolean SaveFileReversed( String fname )
	{	
		int i=0, j=0;
		String line = "";
		File text = new File(fname);
		while(i<List.size())
		{
			Words(i);
			line = "";
			for(j=Words.size()-1; j>=0; j--)
			{
				line = line + Words.get(j) + " ";
			}
			List.remove(i);
			List.add(i, line);
			i++;
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fname))){
			for(i = List.size()-1; i>=0; i--)
			{
				writer.write(List.get(i));
				writer.write('\n');
			}
			 return true;
		 }catch(IOException e) {
		  return false;
		 }
	}
	int CountAppearences( String word )
	{
		int i=0, count=0, j=0;
		while(i<List.size())
		{
			Words(i);
			j=0;
			while(j<Words.size())
			{
				if(Words.get(j).equals(word))
				{
					count++;
				}
				j++;
			}
			i++;
		}
		return count;
	}
	String FindFirstContaining( String word )
	{		
		int i=0, j=0;
		sword = word;
		while(i<List.size())
		{
			Words(i);
			j=0;
			while(j<Words.size())
			{
				if(Words.get(j).equals(word))
				{
					return "Containg" + " " + List.get(i);
				}
				j++;
			}
			i++;
		}
		return "Do not contain";
	}
	String FindNext( )
	{	
		int i=0, j=0, tmp=0;
		String lines ="";
		while(i<List.size())
		{
			Words(i);
			j=0;
			while(j<Words.size())
			{
				if(Words.get(j).equals(sword))
				{
					tmp++;       //aby uniknąć powtórzenia pierwszej linii z tym słowem
					if(tmp != 1)
						lines = lines + " " + i;
				}
				j++;
			}
			i++;
		}
		if(lines != "")
			return lines;
		else
			return "No more line contain this word";
	}
	String FindLongestByWords( )
	{		
		int i=0, max =0;
		String line="";
		while(i<List.size())
		{
			Words(i);
			if(Words.size() > max)
			{
				line = List.get(i);
				max = Words.size();
			}
			i++;
		}
		return "Longest by words" + " " + line;
	
	}	
	String FindLongestByChars( )
	{		
		String line ="";
		int i=0, count=0, j=0, count_max=0;
		while(i<List.size())
		{
			j=0;
			count = 0;
			while(j<List.get(i).length())
			{
				if(List.get(i).charAt(j) != ' ')
					count++;
				j++;
			}
			if(count > count_max)
			{
				count_max = count;
				line = List.get(i);
			}
			i++;
		}
		return "Longest by chars" + " " + line;
	}	
	int Replace( String srd, String dst )
	{		
		int count = 0, i=0, j=0;
		String line = "";
		while(i<List.size())
		{
			Words(i);
			line = "";
			for(j=0; j<Words.size(); j++)
			{
				if(Words.get(j).equals(srd))
				{
					count++;
					Words.remove(j);
					Words.add(j, dst);
				}
				line = line + Words.get(j) + " ";
			}
			if(!List.get(i).equals(line))
			{
				List.remove(i);
				List.add(i, line);
			}
			i++;
			
		}
		return count;
	}
	Boolean RemoveLine( int index )
	{		
		int size = List.size();
		List.remove(List.get(index));
		if(List.size() + 1 == size )
			return true;
		else
			return false;
	}
	Boolean InsertAfter( int index, String line )
	{		
		
		int size = List.size();
		List.add(index, line);
		if(List.size() - 1 == size )
			return true;
		else
			return false;
	}		
	
	void Words(int i)
	{
		int tmp=0, j=0;
		String tmp_word="";
		Words = new ArrayList<String>();
		tmp=0;
		for(j=0; j<List.get(i).length(); j++)
		{
			if(List.get(i).charAt(j) != ' ' && List.get(i).length()-1 != j)
			{
				tmp++;
			}
			else
			{
				if(List.get(i).length()-1 != j)
				{
					tmp_word = List.get(i).substring(j-tmp, j);
					Words.add(tmp_word);
					tmp=0;
				}
				else
				{
					tmp_word = List.get(i).substring(j-tmp, j+1);
					Words.add(tmp_word);
					tmp=0;
				}
					
			}			
		}
	}
}

public class Lista0 {

	private static Scanner in;	

	public static void main(String[] args) {		
		
		in = new Scanner( System.in );  		
        int command = 0;        
    	TextAnalyser analyser = new TextAnalyser();    	
        String in_fname, out_fname, word, src_word, dst_word;               
       
        while ( (command = read_command(true)) != 0 ) 
        {
        	switch ( command ) {
        		case 1: 
        			System.out.print( "Podaj nazw� pliku: ");
        			in_fname = read_str();
        			if( analyser.LoadFile( in_fname ) )
        				System.out.println("OK");
        			else
        				System.out.println("ERR");        			
        			break;
        		case 2: 
        			System.out.print( "Podaj nazw� pliku: ");
        			out_fname = read_str();
        			if( analyser.SaveFile( out_fname ) )
        				System.out.println("OK");
        			else
        				System.out.println("ERR");        			
        			break;
        		case 3: 
        			System.out.print( "Podaj nazw� pliku: ");
        			out_fname = read_str();
        			if( analyser.SaveFileReversed( out_fname ) )
        				System.out.println("OK");
        			else
        				System.out.println("ERR");        			
        			break;
        		case 4: 
        			System.out.print( "Podaj s�owo  do zliczania: ");
        			word = read_str();
        			int count = analyser.CountAppearences(word);
        			System.out.println( Integer.toString(count) );
        			break;
        		case 5: 
        			System.out.print( "Podaj s�owo  do wyszukania wyst�pienia: ");
        			word = read_str();
        			String line = analyser.FindFirstContaining(word);
        			System.out.println( line );
        			break;
        		case 6: 
        			line = analyser.FindNext();
        			System.out.println( line );        			
        			break;
        		case 7: 
        			line = analyser.FindLongestByChars();
        			System.out.println( line );        			
        			break;
        		case 8: 
        			line = analyser.FindLongestByWords();
        			System.out.println( line );        			
        			break;
        		case 9: 
        			System.out.print( "Podaj s�owa: zr�d�owe docelowe: ");
        			word = read_str();
        			String [] words = word.split(" +");
        			if ( words.length != 2) {
        				System.out.println( "B��dne dane" );
        				break;
        			}
        			src_word = words[0];
        			dst_word = words[1];
        			count = analyser.Replace( src_word,  dst_word );
        			System.out.println( Integer.toString(count) );        			
        			break;
        		case 10:
        			int index;
        			System.out.println( "Podaj indeks linii do usuni�cia ");
        			index = read_int();
        			if( analyser.RemoveLine( index ) )
        				System.out.println("OK");
        			else
        				System.out.println("ERR");        			
        			break;
        		case 11:
        			System.out.println( "Podaj indeks linii do wstawienia ");
        			index = read_int();
        			System.out.println( "Wpisz now� lini� ");
        			line = read_str();
        			if( analyser.InsertAfter( index, line ) )
        				System.out.println("OK");
        			else
        				System.out.println("ERR");        			
        			break;
        		default:
        			System.out.println( "B��dna komenda");      
        	}
        }
	}
	
	static int read_command( Boolean debug ) {
		if ( debug ) {
			System.out.println( "0 - koniec" );
			System.out.println( "1 - wczytaj plik <-- nazwa pliku");
			System.out.println( "2 - zapisz plik <-- nazwa pliku");
			System.out.println( "3 - zapisz plik odwr�cony <-- nazwa pliku");
			System.out.println( "4 - zlicz wyst�pienia s�owa <-- s�owo");		
			System.out.println( "5 - znajdz lini� zawieraj�c� s�owo <-- s�owo");
			System.out.println( "6 - znajdz kolejn� linie zawieraj�c� s�owo <-- nazwa pliku");
			System.out.println( "7 - znajdz najd�u�sz� lini� (znaki)");
			System.out.println( "8 - znajdz najd�u�sz� lini� (s�owa)");
			System.out.println( "9 - zamie� s�owo <-- slowo_z s�owo_na");		
			System.out.println( "10 - usu� lini� <-- nr linii");
			System.out.println( "11 - wstaw lini� <-- nr linii   tekst linii");		
			
			System.out.print( "Podaj komend�: ");
		}
		return read_int();
	}

	static String read_str() {
	   return ( in.nextLine() );
	}	

	static int read_int() {
		String s = in.nextLine();
		return Integer.parseInt( s );
	}
}