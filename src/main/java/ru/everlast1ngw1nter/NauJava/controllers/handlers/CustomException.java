package ru.everlast1ngw1nter.NauJava.controllers.handlers;

public class CustomException
{
    private String message;
    private CustomException(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public static CustomException create(Throwable e)
    {
        return new CustomException(e.getMessage());
    }
    public static CustomException create(String message)
    {
        return new CustomException(message);
    }
}
