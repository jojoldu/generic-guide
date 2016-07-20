package guide;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2016-07-20.
 */
public class DataResponse<T> implements Serializable{
    private static final long serialVersionUID = -3647556529780646682L;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
