package com.jo.practice.rxjava;

import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
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
    public void rxjava() throws InterruptedException {
        Observable src1 = Observable.interval(1,TimeUnit.SECONDS);
        Observable src2 = Observable.interval(500,TimeUnit.MILLISECONDS);
        PublishSubject subject = PublishSubject.create();

        src1.map(item -> "A: "+item).subscribe(subject);
        src2.map(item -> "B: "+item).subscribe(subject);
        subject.subscribe(System.out::println);
        Thread.sleep(5000);
    }
}