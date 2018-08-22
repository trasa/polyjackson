# polyjackson

Example of how to serialize and deserialize polymorphic messages, discovered 
at run time, with Jackson.

This uses reflection on startup to identify all the message requests and
responses tagged with "@CustomRequest" or "@CustomResponse", and populates
ObjectMapper so that the mapper understands these polymorphic types.

GameMessages, consisting of a Header and a (polymorphic) Body, can be
serialized and deserialized in a type-specific way via ObjectMapper.

## GameMessage

A `GameMessage` consists of a `GameMessageHeader` and a `Body`. The `GameMessageHeader`
is a plain old POJO without any interesting features, so we won't talk about
it any more.

We have to declare to ObjectMapper what types are valid for getBody() and
how ObjectMapper should store that information so that the resulting
JSON can be deserialized. One way to do this is to enumerate all of the possible
types at compile time - this is the easiest where you know the list of types
is small and doesn't change very frequently.

```
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@messageType")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(value = ExampleRequest.class)
    // other types get listed here
})
public Object getBody() { ... }
```

However, for this example we want to discover the possible values for the
JsonSubType when the application starts. Perhaps we want to scan other
JARs that might be present at runtime. 

## Serialized

A serialized `ExampleRequest` looks like this:

```json
{
  "header": {
    "messageId":"headerId"
  },
  "body":{
    "@messageType":"ExampleRequest",
    "name":"foo",
    "number":12345
  }
}
```

where the identifier "ExampleRequest" is embedded in the body as the property 
we asked for in the `GameMessage` definition.
