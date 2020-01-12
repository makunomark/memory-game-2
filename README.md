## Android Project Template

The intention of this project is to be a template with the industry standard setup of code and libraries.

### Things you should know
1. Google App Indexing:
I have disabled Google App Indexing, for really no reason. If you'd like to allow Google to crawl through your app content, please refer to this [stackoverflow.com](stackoverflow.com) [answer](https://stackoverflow.com/a/34368811/2760868)

2. Content backup:
This template has `fullBackupContent` set to true (enabled). In case you would like for your application not to be backed up, please make the following update 
```diff
- android:fullBackupContent="false"
+ android:fullBackupContent="true"
``` 
3. Dependency injection: Dagger 2 (with dagger-android) has been set up. To start injecting activities, add a block similar but specific to your activity to `ActivityModule.kt` 
```kotlin
@ActivityScope
@ContributesAndroidInjector
fun provideMainActivity(): MainActivity
```

### To Do
- [X] Set up Dagger, and Document
- [X] Set up MVVM
- [X] Setup Room, and Document
