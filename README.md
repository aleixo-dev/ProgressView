# ProgressView

[![](https://jitpack.io/v/Aleixo-Dev/ProgressView.svg)](https://jitpack.io/#Aleixo-Dev/ProgressView)

The "Progress View" library is a custom Android component that enables you to create progress bars with a distinct design. It features a circular progress bar with a ball that represents the current progress and another ball to signify the complete progress. Additionally, you can customize the appearance of the progress view when it's both full and empty.

## Installation ⚙️

```bash
// Register JitPack Repository inside the root build.gradle file
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
     repositories {
	  mavenCentral()
	  maven { setUrl("https://jitpack.io") }
      }
}

// Add ProgressView dependency inside the app's build.gradle file
dependencies {
    implementation("com.github.aleixo-dev:progressview:z.y.z")
}
```
Note: replace x.y.z with one from Release (e.g. 1.0.0).

## Usage 👨‍💻

```
<br.com.nicolas.progress_view.ProgressView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:initialProgress="0"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:maxProgress="10" />

```
<img src="https://github.com/Aleixo-Dev/ProgressView/assets/75820713/6fc4a5fd-40bc-4517-8e46-8da3cf41820c" alt="invisible hcaptcha example" width="300px"/>

## Config Params 📕

The following list contains configuration properties to allows customization.

| Name                                   | Values/Type              | Required | Default | Description |
|----------------------------------------|--------------------------|----------|-------  |----------------------------------------------------------------|
| `maxProgress`                          | Int                      | **Yes**  | -       | This is your max progress view                                                          
| `initialProgress`                      | Int                      | **Yes**  | -       | This specifies the "current progress" of the component.                                 
| `backgroundProgressView`               | Drawable                 | **no**   | True    | This property defines the background of the progress view when it is being filled.                                      
| `backgroundProgressViewEmpty`          | Drawable                 | **no**   | True    | This property sets the background of the progress view when it is empty or has yet to be filled.                        
| `backgroundContainerNumberView`        | Drawable                 | **no**   | True    | This property sets the background of the ball of the current progress view number.                                      
| `backgroundContainerFinalView`         | Drawable                 | **no**   | True    | This property sets the bottom of the ball at the end of the progress view.                                              
