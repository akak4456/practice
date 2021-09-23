package com.makeus.kkongi.architecture;

import com.makeus.kkongi.architecture.dagger.abstract_multi_module.DaggerMultibindsComponent;
import com.makeus.kkongi.architecture.dagger.abstract_multi_module.MultibindsComponent;
import com.makeus.kkongi.architecture.dagger.cafe.Cafe;
import com.makeus.kkongi.architecture.dagger.set_module.DaggerSetComponent;
import com.makeus.kkongi.architecture.dagger.set_module.Foo;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void reactive_programming() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Subject<Object> subject = PublishSubject.create();
        subject.doOnNext(i -> counter.incrementAndGet())
                .doOnNext(i -> counter.decrementAndGet())
                .filter(i -> counter.get() != 0)
                .subscribe(System.out::println,throwable -> throwable.printStackTrace());
        Runnable runnable = () -> {
            for(int i=0;i<100000;i++){
                try{
                    Thread.sleep(i);
                }catch(Throwable throwable){
                    throwable.printStackTrace();
                }
                subject.onNext(i);
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        Thread.sleep(1000);
        System.out.println("종료");
    }
}