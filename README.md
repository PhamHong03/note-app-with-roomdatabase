This error is related to Dagger Hilt and occurs because NoteApplication is not properly provided.

@HiltAndroidApp
class NoteApplication : Application()

<application
    android:name=".NoteApplication"
    ... >
</application>
