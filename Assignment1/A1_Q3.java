/*	CSCI 3110 - Assignemnt 1 - Q3
  	Yansong Li B00755354 
  	Wang Le B00761974 2020-5-12*/
public class A1_Q3 {
   public static void main(String[] args) {
     //red = 0, green = 1, yellow = 2
      
      //Calculate the 3-colouring on Cape Breton Island
      //Cape Breton: a, Inverness: b, Richmond: c, Victoria: d
      int CBIcount = 0;
      for(int a=0; a<3; a++){
         for(int b=0; b<3; b++){
            for(int c=0; c<3; c++){
               if(c == b || c == a){
                  continue;
               }
               for(int d=0; d<3; d++){
                  if(d == a || d == b){
                     continue;
                  }
                  else{
                     CBIcount++;
                  }
               }
            }
         }
      }
      System.out.println("The number of ways to 3-colour Cape Breton Island: "+CBIcount);
      
      //Calculate the 3-colouring on the mainland, firstly, choose "Hants" & "Lunenburg"
      //as the separator. Then calculate south/north respectively.
      int mainland_count = 0, south_mainland_count = 0, north_mainland_count = 0;
      for(int Hants=0; Hants<3; Hants++){
         for(int Lunenburg=0; Lunenburg<3; Lunenburg++){
            if(Lunenburg == Hants){
            	continue;
            }
            //calculate the south part of the mainland
            //int south_mainland_count = 0;
            for(int Annapolis=0; Annapolis<3; Annapolis++){
               if(Annapolis == Lunenburg){
                  continue;
               }
               for(int Digby=0; Digby<3; Digby++){
                  if(Digby == Annapolis){
                     continue;
                  }
                  for(int Kings=0; Kings<3; Kings++){
                     if(Kings == Annapolis || Kings == Hants || Kings == Lunenburg){
                        continue;
                     }
                     for(int Queens=0; Queens<3; Queens++){
                        if(Queens == Digby || Queens == Annapolis || Queens == Lunenburg){
                           continue;
                        }
                        for(int Shelburne=0; Shelburne<3; Shelburne++){
                           if(Shelburne == Queens){
                              continue;
                           }
                           for(int Yarmouth=0; Yarmouth<3; Yarmouth++){
                              if(Yarmouth == Digby || Yarmouth == Shelburne){
                                 continue;
                              }
                              else{
                              south_mainland_count++;
                              }
                           }
                        }
                     }
                  }
               }
            }
            //calculate the north part of the mainland
            //int north_mainland_count = 0;
            for(int Colchester=0; Colchester<3; Colchester++){
               if(Colchester == Hants){
                  continue;
               }
               for(int Halifax=0; Halifax<3; Halifax++){
                  if(Halifax == Colchester || Halifax == Hants || Halifax == Lunenburg){
                     continue;
                  }
                  for(int Cumberland=0; Cumberland<3; Cumberland++){
                     if(Cumberland == Colchester){
                        continue;
                     }
                     for(int Guysborough=0; Guysborough<3; Guysborough++){
                        if(Guysborough == Halifax){
                           continue;
                        }
                        for(int Antigonish=0; Antigonish<3; Antigonish++){
                           if(Antigonish == Guysborough){
                        	   break;
                           }
                           for(int Pictou=0; Pictou<3; Pictou++){
                              if(Pictou == Colchester || Pictou == Antigonish || Pictou == Guysborough){
                            	  break;
                              }
                              else{
                                 north_mainland_count++;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
      mainland_count += south_mainland_count * north_mainland_count;
      System.out.println("The number of ways to 3-colour the mainland: "+mainland_count);
      System.out.println("The number of ways to 3-colour Nova Scotia: "+mainland_count * CBIcount);
   }
}
