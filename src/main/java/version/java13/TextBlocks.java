package version.java13;

public class TextBlocks {

    public static void main(String[] args) {

        String json = """
                {
                    "name" : "Yevhen",
                    "languages" : ["ua", "ru", "en"]
                }
                """;

        System.out.println(json);

        String xml = """
                <dependencyManagement>
                    <dependencies>
                        <dependency>
                            <groupId>software.amazon.awssdk</groupId>
                            <artifactId>bom</artifactId>
                            <version>2.17.46</version>
                            <type>pom</type>
                            <scope>import</scope>
                        </dependency>
                    </dependencies>
                </dependencyManagement>
                """;

        System.out.println(xml);


    }
}
