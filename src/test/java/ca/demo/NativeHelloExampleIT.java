package ca.demo;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeHelloExampleIT extends HelloExampleTest {

    // Execute the same tests but in native mode.
}