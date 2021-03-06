package junit.service;

import api.tool.MigrationTool;
import impl.tool.MigrationToolImpl;
import junit.JUnitMigrationPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MigrationSnapshotTest {

    private static final String JAVA = ".java";
    private static final String EXPECTED_DIRECTORY = "snapshots/%s/expected";
    private static final String ACTUAL_DIRECTORY = "snapshots/%s/actual";

    private MigrationTool tool = new MigrationToolImpl(JUnitMigrationPackage.getInstance());

    @ParameterizedTest(name = "{0} test")
    @ValueSource(strings = {
            "AssertEquals",
            "AssertEqualsStatic",
            "AssertEqualsStaticAsterisk",
            "AssertEqualsText"
    })
    void assertionsTests(String testcaseName) throws Exception {
        String testScope = "assertions";
        Path actual = Paths.get(String.format(ACTUAL_DIRECTORY, testScope), testcaseName + JAVA);
        Path expected = Paths.get(String.format(EXPECTED_DIRECTORY, testScope), testcaseName + JAVA);
        assertEquals(Files.readString(expected), tool.migrate(Files.readString(actual)));
    }

    @ParameterizedTest(name = "{0} test")
    @ValueSource(strings = {
            "AssumeTrue",
            "AssumeTrueStatic",
            "AssumeTrueStaticAsterisk"
    })
    void assumptionsTests(String testcaseName) throws Exception {
        String testScope = "assumptions";
        Path actual = Paths.get(String.format(ACTUAL_DIRECTORY, testScope), testcaseName + JAVA);
        Path expected = Paths.get(String.format(EXPECTED_DIRECTORY, testScope), testcaseName + JAVA);
        assertEquals(Files.readString(expected), tool.migrate(Files.readString(actual)));
    }

    @ParameterizedTest(name = "{0} test")
    @ValueSource(strings = {
            "BeforeEach",
            "AfterEach",
            "BeforeAll",
            "AfterAll",
            "DisabledTest",
            "DisabledClass",
            "NotRelevant",
            "BeforeAndAfterAll",
            "BeforeAndAfterEach",
            "BeforeEachAsterisk",
            "BeforeAndAfterEachAsterisk"
    })
    void annotationsTests(String testcaseName) throws Exception {
        String testScope = "annotations";
        Path actual = Paths.get(String.format(ACTUAL_DIRECTORY, testScope), testcaseName + JAVA);
        Path expected = Paths.get(String.format(EXPECTED_DIRECTORY, testScope), testcaseName + JAVA);
        assertEquals(Files.readString(expected), tool.migrate(Files.readString(actual)));
    }

    @ParameterizedTest(name = "{0} test")
    @ValueSource(strings = {
            "AssertThrows",
            "AssertThrowsImport",
            "AssertThrowsAsteriskImport",
            "AssertThrowsStaticImport",
            "AssertThrowsStaticAsteriskImport"
    })
    void customTests(String testcaseName) throws Exception {
        String testScope = "custom";
        Path actual = Paths.get(String.format(ACTUAL_DIRECTORY, testScope), testcaseName + JAVA);
        Path expected = Paths.get(String.format(EXPECTED_DIRECTORY, testScope), testcaseName + JAVA);
        assertEquals(Files.readString(expected), tool.migrate(Files.readString(actual)));
    }

    @ParameterizedTest(name = "{0} test")
    @ValueSource(strings = {
            "CalculatorTest",
            "FunctionsTest",
            "AlreadyMigratedTest",
            "CommentCalculatorTest"
    })
    void calculatorTests(String testcaseName) throws Exception {
        String testScope = "calculator";
        Path actual = Paths.get(String.format(ACTUAL_DIRECTORY, testScope), testcaseName + JAVA);
        Path expected = Paths.get(String.format(EXPECTED_DIRECTORY, testScope), testcaseName + JAVA);
        assertEquals(Files.readString(expected), tool.migrate(Files.readString(actual)));
    }
}
