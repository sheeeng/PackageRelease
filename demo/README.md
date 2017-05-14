# Demo Example

First, read the main [README.md](../README.md) file.

## Publish Artifacts

```
../gradlew publish
```

## Move Artifacts

```
../gradlew move --info \
-P fromRepo=releases \
-P toRepo=integration \
-P group=com.contoso.orange \
-P file=orange-package \
-P fileVersion=0.0.42
```
