/**
 *  author : bangaru sai jagadeesh
 *
 **/
import java.io.*;
import java.util.*;

	/ *
	  * 		This is the main class . 
	  * /

public class DecisionTree {
	
	public static void generateSplits(List<List<String>> db,List<String> attr_list)
	{
		DecisionTreeConstruction d=new DecisionTreeConstruction();
		if(db.size()==2)return;
		String str=d.maxGain(db,attr_list);
		List<List<List<String>>> temp=d.split(db,str);
		int i;
		for(i=0;i<temp.size();i++)
		{
				List<List<String>> sl=new ArrayList<List<String>>();
				sl=temp.get(i);
				d.printDatabase(sl);
				attr_list=sl.get(0);
				List<String> ms=new ArrayList<String>();
				ms=attr_list;
				generateSplits(sl,attr_list);
				attr_list=ms;
		}
	}
	
	/ *
	  *  function for reading the data from the given data set  
	  * /
	
	public static void readData(List<List<String>> db,List<String> attribute_list)
	{
		Scanner sc=new Scanner(System.in);
		List<String> singles=new ArrayList<String>();	
		List<String> row=new ArrayList<String>();
		List<String> columns=new ArrayList<String>();
		int git=0;
		System.out.println("enter the filename");
		String fn=sc.next();
		File f=new File(fn);
		int cd=0;
		try
		{

			Scanner sc1=new Scanner(f);
			while(sc1.hasNext())
			{
				row=new ArrayList<String>();
				String gs=sc1.nextLine();
				StringTokenizer gt=new StringTokenizer(gs,",");
				int gj=0;
				while(gt.hasMoreTokens())
				{
					String mys=gt.nextToken();
					row.add(mys);
									
				}
				db.add(row);	
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(" input.csv is not placed in the correct directory ");
			System.exit(0);
		}
	}
	
	/* function for displaying dataset db , which is passed as a parameter */
	
	public static void displayData(List<List<String>> db,List<String> attribute_list)
	{
		int i=0;
		System.out.println("**************************************************************************************************************************************************");
		int cnt=0;
		for(List<String> list:db)
		{
			
			for(String str:list)
			{
				if(i==0)
				{
					attribute_list.add(str);
				}
				
				cnt=20-str.length();
				while(cnt!=0)
				{
					cnt--;
					System.out.print(" ");		
				}
				System.out.print(str+"\t");
			}
			i++;
			System.out.println();
		}
		System.out.println("**************************************************************************************************************************************************");
	}
	
	/* Driver Code */
	
	public static void main(String args[])
	{
		List<List<String>> db=new ArrayList<List<String>>();
		List<String> attribute_list=new ArrayList<String>();

		DecisionTreeConstruction d=new DecisionTreeConstruction();
		
		readData(db,attribute_list);
		
		displayData(db,attribute_list);
		
		Node root=d.generateDecisionTree(db, attribute_list);
		Node.traverse(root);
	}
}
