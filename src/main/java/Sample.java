import java.util.Arrays;

class User{
    String name;
    String[]arr={"hi","hello"};
    public String[] getAdd() {
        return arr;
    }

    public void setAdd(String[] add) {
        for(int i=0;i<arr.length;i++){
            arr[i]=add[i];
        }
        this.arr=add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
public class Sample {
    public static void main(String[] args) {
        String[]addr={"j","k"};
        User e=new User();
        System.out.println(Arrays.asList(e.getAdd()));
        e.setAdd(addr);
        System.out.println(Arrays.asList(e.getAdd()))   ;
    }
}
