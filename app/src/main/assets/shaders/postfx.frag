#version 320 es
precision highp float;
in vec2 v_uv;
uniform sampler2D u_color;
out vec4 fragColor;
void main() {
    vec3 color = texture(u_color, v_uv).rgb;
    float dist = distance(v_uv, vec2(0.5));
    color *= smoothstep(0.8, 0.2, dist);
    color = color / (color + vec3(1.0));
    fragColor = vec4(color, 1.0);
}
