All living handlers need to be formatted as such `LivingTheActionHandler`
All player handlers need to be formatted as such `PlayerTheActionHandler`

all player handlers need to be initialized in the `CommonEventHandler`

If there isn't a event for the action would would like to use then you will create a method in
LivingHandlerBase or PlayerHandlerBase. If it is a event that could be used for both living
and player handlers then create one for both.

##### Player events are formatted as such 
```
public void playerAction(Object input){}
```
##### Living events are formatted as such
```
public void livingAction(Object input){}
```

When you create a handler, you will just override the events that you would like to use in your handler

##### Handlers will look like this
```java
public class TypeAction extends TypeHandlerBase{
    @Override
    public void typeAction(Object input){
        //Put actions here
    }
}
```
##### Handlers need to be instantiated
```java
public static TypeHandlerBase TYPE_ACTION_HANDLER = new TypeYourActionHandler();
```
This will be automatically added to the handler queue