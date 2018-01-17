package GUI;


class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
   }
   
   public void run() {
      System.out.println("Running " +  threadName );
      try {
        while(gui.sqlthred){
            System.out.println("Thread: " + threadName + ", " );
            // Let the thread sleep for a while.
            while(!MySQL_101.isModified()  && gui.sqlthred){
        	
        		 Thread.sleep(5000);
         }
            Thread.sleep(5000);
       
        }
       
      } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
      }
      System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
}
