package ffzim.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shadowify {
    private static final String VERT = "uniform mat4 u_projTrans;\n\nattribute vec4 a_position;\nattribute vec2 a_texCoord0;\nattribute vec4 a_color;\n\nvarying vec4 v_color;\nvarying vec2 v_texCoord;\n\nuniform vec2 u_viewportInverse;\n\nvoid main() {\n    gl_Position = u_projTrans * a_position;\n    v_texCoord = a_texCoord0;\n    v_color = a_color;\n}";
    private static final String FRAG = "uniform sampler2D u_texture;\n\nvarying vec4 v_color;\nvarying vec2 v_texCoord;\n\nconst vec3 TINT = vec3(0.8, 1.2, 0.8);\nvoid main() {\n    vec4 texColor = texture2D(u_texture, v_texCoord);\n    \n    float gray = dot(texColor.rgb, vec3(0.299, 0.587, 0.114));\n    vec3 green = vec3(gray) * TINT;\n    \n\t texColor.rgb = mix(texColor.rgb, green, 1.0);\n    gl_FragColor = texColor * v_color;\n}";
    public static ShaderProgram program = new ShaderProgram("uniform mat4 u_projTrans;\n\nattribute vec4 a_position;\nattribute vec2 a_texCoord0;\nattribute vec4 a_color;\n\nvarying vec4 v_color;\nvarying vec2 v_texCoord;\n\nuniform vec2 u_viewportInverse;\n\nvoid main() {\n    gl_Position = u_projTrans * a_position;\n    v_texCoord = a_texCoord0;\n    v_color = a_color;\n}", "uniform sampler2D u_texture;\n\nvarying vec4 v_color;\nvarying vec2 v_texCoord;\n\nconst vec3 TINT = vec3(1.0, 0.4, 0.8);\nvoid main() {\n    vec4 texColor = texture2D(u_texture, v_texCoord);\n    \n    float gray = dot(texColor.rgb, vec3(0.299, 0.587, 0.114));\n    vec3 green = vec3(gray) * TINT;\n    \n\t texColor.rgb = mix(texColor.rgb, green, 1.0);\n    gl_FragColor = texColor * v_color;\n}");

    public Shadowify() {
    }
}
