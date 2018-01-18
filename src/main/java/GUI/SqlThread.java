package  main.java.GUI;


class SqlThread implements Runnable {
   private Thread t;
   
   
   SqlThread( ) {
     
      System.out.println("Creating "  );
   }
   
   public void run() {
      System.out.println("Running " );
      try {
        while(gui.sqlthred){
            System.out.println("Thread:workink ");
            // Let the thread sleep for a while.
            if(OrgSql.TimeUpdateCheck()){
            	System.out.println("Reconize change");
            	gui.filter.getDataBase().clear();
				gui.filter.thread();
				gui.informationTxt.setText(gui.listInfo.toString());
        		 
         }
            Thread.sleep(15000);
       
        }
       
      } catch (InterruptedException e) {
         System.out.println("Thread  interrupted.");
      }
      System.out.println("Threadexiting.");
   }
   
   public void start () {
      System.out.println("Starting " );
      if (t == null) {
         t = new Thread (this);
         t.start ();
      }
   }
}
