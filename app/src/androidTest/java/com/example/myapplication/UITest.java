package com.example.myapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.regex.Pattern.matches;

import android.os.SystemClock;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAppName() {
        onView(withText("CardiacRecorder")).check(ViewAssertions.matches(isDisplayed()));
    }




    @Test
    public void addData() {

        SystemClock.sleep(3000);
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.bp)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.sys)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.dis)).perform(ViewActions.typeText("80"));
        Espresso.pressBack(); //hide keyboard
        onView(withId(R.id.up)).perform(click());

    }


    @Test
    public void updateData() {

        SystemClock.sleep(2000);
        onView(withId(R.id.stat)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.update)).perform(click());
        onView(withId(R.id.bp)).perform(ViewActions.typeText("70"));
        onView(withId(R.id.sys)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.dis)).perform(ViewActions.typeText("80"));
        Espresso.pressBack(); //hide keyboard
        onView(withId(R.id.up)).perform(click());

    }

    @Test
    public void deleteData() {
        SystemClock.sleep(3000);
        onView(withId(R.id.stat)).perform(click());
        onView(withId(R.id.delete)).perform(click());

    }
}
