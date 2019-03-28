package securitynews.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutors {

    private final Executor diskIOThread;

    private final Executor mainThread;

    AppExecutors(Executor diskIOThread, Executor mainThread) {
        this.diskIOThread = diskIOThread;
        this.mainThread = mainThread;
    }

    public AppExecutors(){
        this(new DiskIOThreadExector(), new MainThreadExecutor());
    }

    public Executor getDiskIOThread(){
        return this.diskIOThread;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor{
        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            handler.post(runnable);
        }
    }

    public static class DiskIOThreadExector implements Executor{

        private final Executor mDiskIO;

        public DiskIOThreadExector(){
            this.mDiskIO = Executors.newSingleThreadExecutor();
        }

        @Override
        public void execute(@NonNull Runnable runnable) {
            mDiskIO.execute(runnable);
        }
    }

}
