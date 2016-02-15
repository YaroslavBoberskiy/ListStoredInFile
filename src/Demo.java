public class Demo {

    public static void main(String[] args) {
        FileArrayList fal = new FileArrayList();

        fal.clear();

        fal.add("1");
        fal.add("2");
        fal.add("3");
        fal.add("4");

        System.out.println("size: "+fal.size());

        for (String s : fal) {
            System.out.println(s);
        }

        FileArrayList fal2 = new FileArrayList();

        for (String s : fal2) {
            System.out.println(s);
        }
    }
}
