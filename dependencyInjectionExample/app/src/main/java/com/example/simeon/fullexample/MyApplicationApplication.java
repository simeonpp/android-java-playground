package com.example.simeon.fullexample;

import android.app.Application;

import com.example.simeon.fullexample.activities.BooksListActivity;
import com.example.simeon.fullexample.config.ConfigModule;
import com.example.simeon.fullexample.daggerModules.DataModule;

import dagger.Component;

public class MyApplicationApplication extends Application {
    private ApplicationComponent component;

    public void onCreate() {
        super.onCreate();

        this.component = DaggerMyApplicationApplication_ApplicationComponent.builder()
            .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {DataModule.class, ConfigModule.class})
    public interface ApplicationComponent {
        void inject(BooksListActivity booksListActivity); // inject into main activities
    }
}
