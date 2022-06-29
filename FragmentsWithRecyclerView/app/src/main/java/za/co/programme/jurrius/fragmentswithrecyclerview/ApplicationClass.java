package za.co.programme.jurrius.fragmentswithrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application
{
    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();

        people = new ArrayList<Person>();
        people.add(new Person("Chuck Norris", "9820098200"));
        people.add(new Person("John Rambo", "9830098300"));
        people.add(new Person("Nelson Mandela", "9840098400"));
    }
}
