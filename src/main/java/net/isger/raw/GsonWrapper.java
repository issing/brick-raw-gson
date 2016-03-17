package net.isger.raw;

public class GsonWrapper implements Wrapper {

    public Artifact wrap(Raw raw) {
        GsonArtifact artifact = new GsonArtifact(raw);
        if (artifact.transform() == null) {
            artifact = null;
        }
        return artifact;
    }
}
