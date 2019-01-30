
import java.util.*;

/*
 *  This class contains the implementation of the construction of the decision tree .
 */

public class DecisionTreeConstruction
{
	static List<String> msl=new ArrayList<String>();
	
	
	public Node generateDecisionTree(List<List<String>> db,List<String> attribute_list)
	{
		Map<String,Integer> id=new HashMap<String,Integer>();
		int i;String ent="";
		Node n=new Node();
		
		for(i=1;i<db.size();i++)
		{
          List<String> row1=db.get(i);
          ent=row1.get(row1.size()-1);
           int value=0;
			if(id.get(ent)!=null)
				value=id.get(ent);
			id.put(ent,value+1);
		}
		
		if(id.size()==1)
		{
			Set<String> set=id.keySet();
			Iterator it=set.iterator();
			n.Name=(String) it.next();
			return n;
		}
		
		if(attribute_list.isEmpty())
		{
			n.Name=Collections.max(id.entrySet(),(entry1,entry2)->entry1.getValue()-entry2.getValue()).getKey();
			return n;
		}
		
		/*
		 * The dataset is split based on the attribute having maximum information gain .
		 */
		
		String str=maxGain(db,attribute_list);
		n.Name=(str+"?");
		List<List<List<String>>> temp=split(db,str);
		
		for(i=0;i<temp.size();i++)
		{
				
				List<List<String>> sl=new ArrayList<List<String>>();
				sl=temp.get(i);
				printDatabase(sl);
				String ed=sl.get(sl.size()-1).get(0);
				sl.remove(sl.size()-1);
				
				if(sl.size()==1)
				{
					Node m=new Node();
					m.Name=Collections.max(id.entrySet(),(entry1,entry2)->entry1.getValue()-entry2.getValue()).getKey();
					n.edges.add(m.Name);
					
					n.children.add(m);
					m.parent=n.Name;
					m.edge=ed;
					return n;
				}
				attribute_list=sl.get(0);
				List<String> ms=new ArrayList<String>();
				Node m=new Node();
				m=generateDecisionTree(sl,attribute_list);
				n.children.add(m);
				m.parent=n.Name;
				m.edge=ed;
		}
		return n;
	}
	
	/* attribute selection is done using information gain  */
	
	public double infoGain(Map<String,Integer> id)
	{
		double res=0;
		int n=id.size(),i=0;
		int dn=0;
		for(Map.Entry<String,Integer> entry:id.entrySet())
		{
			dn+=entry.getValue();
		}
		for(Map.Entry<String,Integer> entry:id.entrySet())
		{
			
			res-=((double)entry.getValue()/dn)*((double)Math.log((double)entry.getValue()/dn)/((double)Math.log(2)));
			
		}
		return res;
	}
	
	/*function for calculating attribute giving the maximum gain for a given dataset or a subset of the dataset */  
	
	public String maxGain(List<List<String>> pd,List<String> attr_list)
	{
		
		Map<String,Integer> id=new HashMap<String,Integer>();
		double dn=pd.size()-1,maxGain=-999;
		String ent="";
		String best="";
		double attr_info = 0,attr_gain=0;
		int i,j;

    for(i=1;i<pd.size();i++)
		{
        List<String> row1=pd.get(i);
        ent=row1.get(row1.size()-1);
			  int value=0;
			if(id.get(ent)!=null)
				value=id.get(ent);
			id.put(ent,value+1);
		}
		
		double info=infoGain(id);	
		
		for(i=1;i<attr_list.size()-1;i++)
		{	
			String str=attr_list.get(i);
			int index=i;
			id=new HashMap();
	
			for(j=1;j<pd.size();j++)
			{
					List<String> row1=pd.get(j);
					ent=row1.get(index);
					int value=0;
					if(id.get(ent)!=null)
						value=id.get(ent);
					id.put(ent,value+1);
			}
			
			Set<String> entries=id.keySet();
			Map<String,Integer> mit=new HashMap<String,Integer>();
			attr_info=0;
			
			for(String attr:entries)
			{
				index=i;
				mit=new HashMap();
				for(j=1;j<pd.size();j++)
				{
					List<String> dr=pd.get(j);
					if(dr.get(index).equals(attr))
					{
						ent=dr.get(dr.size()-1);
						int value=0;
						if(mit.get(ent)!=null)
							value=mit.get(ent);
						 mit.put(ent,value+1);
					}						
				}
				attr_info+=((double)(id.get(attr))/(double)(dn))*infoGain(mit);
			}
			attr_gain=info-attr_info;
			if(attr_gain>maxGain)
				{
				maxGain=attr_gain;
				best=str;
				}
		}
		return best;
		
	}
	
		/* this is the function for printing the dataset */
	
	public void printDatabase(List<List<String>> db)
	{
		System.out.println("*********************************************************");
		for(List<String> l:db)
		{
			for(int i=0;i<l.size();i++)
			{
				System.out.print(l.get(i)+"\t");
			}
			System.out.println();	
		}
		System.out.println("********************************************************");
		
	}
	
	/* 
	 * this function splits the given dataset sdb into different sub-datasets 
	 * based on the attribute attr which is passed as a parameter . 
	 * 
	 */
	
	public List<List<List<String>>> split(List<List<String>> sdb,String attr)
	{
		List<List<List<String>>> splits=new ArrayList<List<List<String>>>();
		Set<String> domain=new HashSet<String>();
		List<String> labels=new ArrayList<String>();
		labels=sdb.get(0);
		int i;
		
		if(labels.size()<4)return splits;
		
		for(i=1;i<labels.size()-1;i++)
		{
			String str=labels.get(i);
			if(attr.equals(str))
			{
				break;
			}
		}
		labels.remove(attr);
		int j;
		
		for(j=1;j<sdb.size();j++)
		{
			List<String> lt=sdb.get(j);
			domain.add(lt.get(i));
		}
		List<String> ex=new ArrayList<String>();
		List<List<String>> temp=new ArrayList<List<String>>();
		
		for(String st:domain)
		{
			temp=new ArrayList<List<String>>();
			labels=new ArrayList<String>(labels);
			msl.add(st);
			temp.add(labels);
			for(j=0;j<sdb.size();j++)
			{
			
				List<String> lt=sdb.get(j);
				if((lt.get(i)).equals(st))
				{
					lt.remove(i);
					temp.add(lt);
				}
			}
			ex=new ArrayList();
			ex.add(st);
			temp.add(ex);
			splits.add(temp);	
		}
			return splits;
	}
}
