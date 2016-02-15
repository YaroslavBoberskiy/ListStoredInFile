/**
 * Created by YB on 14.02.2016.
 */
public interface SimpleList {

    public void add(Object object);

    public void add(int index, Object object);

    public String get(int idx);

    public void  remove(Object object);

    public void remove(int idx);

    public void clear();

    public int size();

}
