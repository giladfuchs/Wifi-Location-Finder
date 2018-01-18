package main.java.GUI;
import java.nio.file.*;

public class NioFileSupport 
{
	/**
	 * http://www.thecoderscorner.com/team-blog/java-and-jvm/java-nio/36-watching-files-in-java-7-with-watchservice/
	 * This Runnable is used to constantly attempt to take from the watch
	 * queue, and will receive all events that are registered with the
	 * fileWatcher it is associated. In this sample for simplicity we
	 * just output the kind of event and name of the file affected to
	 * standard out.
	 */

	public static class MyWatchQueueReader implements Runnable 
	{
		/** the watchService that is passed in from above */
		private WatchService myWatcher;
        
		public MyWatchQueueReader(WatchService myWatcher) {
			this.myWatcher = myWatcher;
		}

		/**
		 * In order to implement a file watcher, we loop forever
		 * ensuring requesting to take the next item from the file
		 * watchers queue.
		 */
		@Override
		public void run() {
			
			try {
				// get the first event before looping
				WatchKey key = myWatcher.take();
				
				while(key != null) {
					// we have a polled event, now we traverse it and
					// receive all the states from it
					if(!gui.listen)
						break;
					
					for (WatchEvent event : key.pollEvents() ){   
						if(gui.listen){
						System.out.printf("Received %s event for file: %s\n", event.kind(), event.context()); 
						gui.filter.getDataBase().clear();
						gui.filter.thread();
						gui.informationTxt.setText(gui.listInfo.toString());
						System.out.printf("filter.getDirPaththread() = "+gui.filter.getDataBase().size());
					}
					}
				
                  
					key.reset();
					key = myWatcher.take();
                   
                    	
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
		
			System.out.println("Stopping thread");
		}
	}
}