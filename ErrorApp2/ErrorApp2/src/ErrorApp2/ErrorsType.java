package ErrorApp2;

public class ErrorsType
{
    public int id;
    public String name;
    public String Desc;
    ErrorsType(int id, String name, String Desc)
    {
        this.id = id;
        this.name = name;
        this.Desc = Desc;
    }
    @Override
    public String toString()
    {
        return String.format("ID: %s | Название: %s | Описание: %s", this.id, this.name, this.Desc);
    }
}
