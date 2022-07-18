package zad5;


class ProblemGenerator extends Thread {
    private Problem problem;
    private static Object sharedResource0 = new Object();
    private static Object sharedResource1 = new Object();
    private static Object sharedResource2 = new Object();
    
    ProblemGenerator set(Problem theProblem){
        problem = theProblem;
        return this;
    }

    public void run(){
        try{
            switch(problem){
                case LIVELOCK:
                    System.out.println("Starting LiveLock...");
                    LiveLock ll0 = new LiveLock();
                    LiveLock ll1 = new LiveLock();
                    ll0.setEvilTwin(ll1);
                    ll1.setEvilTwin(ll0);
                    ll0.start();
                    ll1.start();
                break;
                
                case DEADLOCK:
                    System.out.println("Starting DeadLock...");
                    (new DeadLock(sharedResource0, sharedResource1)).start();
                    Thread.sleep(1000);
                    (new DeadLock(sharedResource1, sharedResource0)).start();
                break;
    
                case STARVATION:
                    System.out.println("Starting Starvation...");
                    (new Starvation(sharedResource0)).start();
                    (new Starvation(sharedResource0)).start();
                break;
            }

            Thread.sleep(10000);
            System.out.println("Looks like I stucked...");
            System.exit(0);
        }catch(InterruptedException e){e.printStackTrace();}
    }

    class DeadLock extends Thread{
        private Object obj0, obj1;

        DeadLock(Object theObjec0, Object theObject1){
            obj0 = theObjec0;
            obj1 = theObject1;
        }

        public void cancel(){
            interrupt();
        }

        public void run(){
            String name = Thread.currentThread().getName();

            
            System.out.println(name + " acquiring lock on "+obj0);
            synchronized (obj0) {
                System.out.println(name + " acquired lock on "+obj0);
                work();
                System.out.println(name + " acquiring lock on "+obj1);
                synchronized (obj1) {
                    System.out.println(name + " acquired lock on "+obj1);
                    work();
                }
                System.out.println(name + " released lock on "+obj1);
            }
            System.out.println(name + " released lock on "+obj0);
            System.out.println(name + " finished execution.");
        }

        public void work(){
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    class LiveLock extends Thread{

        private Boolean     active;
        private LiveLock    evilTwinThread;

        LiveLock(){
            active = true;
        }

        public void setEvilTwin(LiveLock evilTwin){
            evilTwinThread = evilTwin;
        }

        public boolean isActive(){
            return active;
        }

        public void run(){
            if(evilTwinThread == null) System.out.println("Where is my evil twin??");
            else{
                while (active) {
               
                    // My evil twin must leave!
                    try{
                        if (evilTwinThread.isActive()) {
                            Thread.sleep(1000);                 
                            System.out.println(
                                "Please leave me alone " + evilTwinThread.getName() + " !");
                            continue;
                        }
                    }catch(InterruptedException e){}
    
                    // My evil twin is has finally left!
                    active = false;               
                    System.out.printf(
                        "My evil twin is not active now I can leave!"); 
                }
            }
        }

    }

    class Starvation extends Thread{
        private Object sharedObject;

        Starvation(Object theSharedObject){
            this.sharedObject = theSharedObject;
        }
        
        public void run(){
            System.out.println("Aquering lock for shared object by " + Thread.currentThread().getName());
            synchronized(sharedObject){
                System.out.println("Locked aquired by " + Thread.currentThread().getName() + " and never realesed...");
                while(true);
            }
        }
    }


    enum Problem{
        LIVELOCK,
        DEADLOCK,
        STARVATION
    }
}

