package org.onepf.opfmaps.model;

/**
 * Created by akarimova on 15.06.15.
 */
public class OPFShape extends OPFMapObject {
    private float zIndex = 0.0f;

    public void zIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public float getzIndex() {
        return zIndex;
    }

    public static abstract class Builder<T extends OPFShape> implements org.onepf.opfmaps.model.Builder<T> {
        private T shape;

        public Builder() {
            this.shape = createShape();
        }

        public Builder<T> setzIndex(float zIndex) {
            shape.zIndex(zIndex);
            return this;
        }

        protected abstract T createShape();

        protected T getShape() {
            if (shape == null) {
                shape = createShape();
            }
            return shape;
        }
    }
}
