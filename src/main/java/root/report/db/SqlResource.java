package root.report.db;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SqlResource extends AbstractResource implements WritableResource {
    private final String jarPath;
    private final String jarInnerPath;
    private final File file;

    public SqlResource(String jarPath, String jarInnerPath) {
        this.jarPath = jarPath;
        this.jarInnerPath = jarInnerPath;
        this.file = new File(jarPath+"!"+File.separator+jarInnerPath);
    }
    public final String getPath() {
        return jarPath+"!"+File.separator+jarInnerPath;
    }

    public boolean exists() {
        return true;
    }

    public boolean isReadable() {
        return true;
    }
    public InputStream getInputStream() throws IOException {
        JarFile jarFile = new JarFile(jarPath);
        JarEntry entry = jarFile.getJarEntry(jarInnerPath);
        return jarFile.getInputStream(entry);
    }
    public boolean isWritable() {
        return true;
    }
    public OutputStream getOutputStream() throws IOException {
        return null;
    }
    public URL getURL() throws IOException {
        return this.file.toURI().toURL();
    }

    public URI getURI() throws IOException {
        return this.file.toURI();
    }
    public ReadableByteChannel readableChannel() throws IOException {
        try {
            return FileChannel.open(this.file.toPath(), StandardOpenOption.READ);
        } catch (NoSuchFileException var2) {
            throw new FileNotFoundException(var2.getMessage());
        }
    }

    public WritableByteChannel writableChannel() throws IOException {
        return FileChannel.open(this.file.toPath(), StandardOpenOption.WRITE);
    }

    public long contentLength() throws IOException {
        return this.file.length();
    }
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.jarInnerPath, relativePath);
        return new FileSystemResource(pathToUse);
    }

    public String getFilename() {
        return this.file.getName();
    }

    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
    public boolean equals(Object obj) {
        return obj == this || obj instanceof SqlResource && this.jarInnerPath.equals(((SqlResource)obj).jarInnerPath);
    }

    public int hashCode() {
        return this.jarInnerPath.hashCode();
    }
}
