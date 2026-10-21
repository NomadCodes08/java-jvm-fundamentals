public class JVMExecutionDemo {

    public static void main(String[] args) {
        String language = "JAVA";

        System.out.println(" Operating System : " + System.getProperty("os.name"));
        System.out.println(" Language : " + language);
        System.out.println(" Language Version : " + System.getProperty("java.version"));
        System.out.println(" JVM Name : " + System.getProperty("java.vm.name"));
        System.out.println(" JVM Version : " + System.getProperty("java.vm.version"));

    }
}
