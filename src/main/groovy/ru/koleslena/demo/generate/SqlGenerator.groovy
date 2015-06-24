package ru.koleslena.demo.generate

/**
 * Created by elenko on 24.06.15.
 */
class SqlGenerator {

    static private PrintStream getOutput (String target) {
        File targetFile = new File(target)
        File parentDir = targetFile.parentFile;

        if (!parentDir.directory && !parentDir.mkdirs()) {
            throw new Exception("unable to create directory ${parentDir.absolutePath}")
        }

        FileOutputStream out = new FileOutputStream(targetFile);
        PrintStream fileWriter = new PrintStream(true, out);
        fileWriter
    }

    public static void main(String[] args) {
        PrintStream pout = getOutput("src/main/resources/sql/dbSeed.sql")
        seedDb(pout)
        //seedDb(System.out)
    }

    static void seedDb(PrintStream out) {
        for(int i = 0; i < 100; i++) {
            out.printf("insert into Item (content, name, published, id) values ('content %d', 'name %d', %s , '%d');%n", i, i, (i & 1) == 0 ? "TRUE" : "FALSE", i);
        }
    }
}
