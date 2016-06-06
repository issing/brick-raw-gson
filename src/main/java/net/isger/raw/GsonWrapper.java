package net.isger.raw;

public class GsonWrapper extends AbstractWrapper {

    public Artifact wrap(Raw raw) {
        GsonArtifact artifact = new GsonArtifact(raw);
        if (artifact.transform(Object.class) == null) {
            artifact = null;
        }
        return artifact;
    }

}
