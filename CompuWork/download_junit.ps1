$baseUrl = "https://repo1.maven.org/maven2"
$dependencies = @(
    "org/junit/jupiter/junit-jupiter/5.8.1/junit-jupiter-5.8.1.jar",
    "org/junit/jupiter/junit-jupiter-api/5.8.1/junit-jupiter-api-5.8.1.jar",
    "org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar",
    "org/junit/platform/junit-platform-commons/1.8.1/junit-platform-commons-1.8.1.jar",
    "org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar",
    "org/junit/jupiter/junit-jupiter-params/5.8.1/junit-jupiter-params-5.8.1.jar",
    "org/junit/jupiter/junit-jupiter-engine/5.8.1/junit-jupiter-engine-5.8.1.jar",
    "org/junit/platform/junit-platform-engine/1.8.1/junit-platform-engine-1.8.1.jar"
)

foreach ($dep in $dependencies) {
    $url = "$baseUrl/$dep"
    $filename = Split-Path $dep -Leaf
    $output = "lib\$filename"
    Write-Host "Downloading $filename..."
    Invoke-WebRequest -Uri $url -OutFile $output
}

Write-Host "Done downloading JUnit dependencies."
