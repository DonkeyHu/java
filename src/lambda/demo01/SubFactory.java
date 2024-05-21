package lambda.demo01;

public class SubFactory implements Factory{
    @Override
    public Object getObject() {
        return new User();
    }
}
