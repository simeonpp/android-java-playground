Steps to set up dagger
1) Create new class with the name: DaggerYourApplicationNameApplication which extend Application
2) Set in manifesto andoird:name=".YourApplicationNameApplication"
3) In the created class (step 1) create public void onCreate method to set up dagger
public void onCreate() {
        super.onCreate(); 
}
4) Create in the same file
@Component
    public interface ApplicationComponent {
        void inject(BooksListActivity booksListActivity); // inject into main activities
    }
5) And @Inject on all places where things are inject (or comment them out) & Build the project due to generates code automatically
6) Check code
