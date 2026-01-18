package suike.suikecherry.inter;

import com.google.common.collect.ImmutableMap;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Map;

public interface IMethod {

    default Map<String, String[]> getMethods() {
        return ImmutableMap.<String, String[]>builder().build();
    }

    MethodVisitor setMethodType(String type);

    default String[] getInterfaces(String[] interfaces) {
        return interfaces;
    }

    default void addValue(ClassVisitor visitor) {}
}