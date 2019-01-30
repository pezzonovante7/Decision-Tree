# Decision-Tree
Implementation of the decision tree algorithm in java .



Algorithm: Generate decision tree. Generate a decision tree from the training tuples of data partition, D. 
        Input:

          Data partition, D, which is a set of training tuples and their associated class labels;
          
          attribute list, the set of candidate attributes;
          
          Attribute selection method, a procedure to determine the splitting criterion that “best” partitions the data tuples into individual classes.
          
          This criterion consists of a splitting attribute and, possibly, either a split-point or splitting subset.

        Output: A decision tree. 
        
    Method:
    
        (1) create a node N; 
        
        (2) if tuples in D are all of the same class, C, then 
        
        (3) return N as a leaf node labeled with the class C; 
        
        (4) if attribute list is empty then 
        
        (5) return N as a leaf node labeled with the majority class in D; // majority voting 
        
        (6) apply Attribute selection method(D, attribute list) to ﬁnd the “best” splitting criterion; 
        
        (7) label node N with splitting criterion;
   
        (8) foreach outcome j of splitting criterion // partition the tuples and grow subtrees for each partition 
        
        (9) let Dj be the set of data tuples in D satisfying outcome j; // a partition 
        
        (10) if Dj is empty then
        
        (11) attach a leaf labeled with the majority class in D to node N;
        
        (12) else attach the node returned by Generate decision tree(Dj, attribute list) to node N; endfor
        
        (13) return N;
        

    Execution:
    
        In eclipse : 
          
                    inlcude the input file input.csv in the direcoty containing the src folder and 
                    place all the .java files in the src folder.
                    
        In cmd : 
          
                    include the input file and .java files in the same directory and run the commands,

                                  $ javac DecisionTree.java

                                  $ java DecisionTree
                                  
                                  
                                  
Happy coding , Thank you .                                  
                                
