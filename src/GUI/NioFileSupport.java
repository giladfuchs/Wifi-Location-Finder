package GUI;

import java.nio.file.*;
import java.text.ParseException;

import Filter.Filter;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;;

public class NioFileSupport 
{
    /** change this as appropriate for your file system structure. */
    //public static final String DIRECTORY_TO_WATCH = "c:/OOP/WigleWifi_files";

    /*public static void main(String[] args) throws Exception 
    {
        // get the directory we want to watch, using the Paths singleton class
        Path toWatch = Paths.get(DIRECTORY_TO_WATCH);
        if(toWatch == null) {
            throw new UnsupportedOperationException("Directory not found");
        }

        // make a new watch service that we can register interest in
        // directories and files with.
        WatchService myWatcher = toWatch.getFileSystem().newWatchService();

        // start the file watcher thread below
        MyWatchQueueReader fileWatcher = new MyWatchQueueReader(myWatcher);
        Thread th = new Thread(fileWatcher, "FileWatcher");
        th.start();

        // register a file
        toWatch.register(myWatcher, ENTRY_CREATE, ENTRY_MODIFY,ENTRY_DELETE);
        th.join();
    }*/
    /**
     * This Runnable is used to constantly attempt to take from the watch
     * queue, and will receive all events that are registered with the
     * fileWatcher it is associated. In this sample for simplicity we
     * just output the kind of event and name of the file affected to
     * standard out.
     */
    public static class MyWatchQueueReader implements Runnable 
    {
    	private Filter filter=new Filter();

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
                    for (WatchEvent event : key.pollEvents()){                     
                        System.out.printf("Received %s event for file: %s\n", event.kind(), event.context()); 
                      
                        //gui.threadSignal = true;
                        try {
                        	while(filter.getDataBase().size()>0)
            					filter.getDataBase().remove(0);
                        	filter.setCountfilter(0);
                        	for (int i = 0; i < gui.listInfo.size(); i++) {
                        		String s1=gui.listInfo.get(i).getS1();
                        		String s2=gui.listInfo.get(i).getS2();
                        		String s3=gui.listInfo.get(i).getS3();
                        		boolean andor=true;
                        		if(gui.listInfo.get(i).getKind().equals("And"))
                        			andor=false;
                        		boolean not=false;
                        		if(gui.listInfo.get(i).getType().equals("Not"))
                        			not=true;
                        		int filterType = 3;
                        		if(gui.listInfo.get(i).getMode().equals("Name"))
                        			filterType = 1;
                        		else if(gui.listInfo.get(i).getMode().equals("Date"))
                        			filterType = 2;
                        		
								filter.filtermain(andor, not, filterType, s1, s2, s3);
							}
                        	System.out.println(gui.listInfo.get(0).toString());
                        	System.out.printf("filter.getDirPaththread() = "+filter.getDirPaththread());
							filter.readq2(filter.getDirPaththread());
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
												
                    }
                    System.out.println("------");
                    
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