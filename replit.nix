{ pkgs }: {
    deps = [
        pkgs.sudo
        pkgs.ed
        pkgs.graalvm17-ce
        pkgs.maven
        pkgs.replitPackages.jdt-language-server
        pkgs.replitPackages.java-debug
    ];
}