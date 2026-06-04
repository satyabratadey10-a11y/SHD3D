#version 320 es
precision highp float;
in vec3 v_texcoord;
out vec4 fragColor;
void main() {
    fragColor = vec4(mix(vec3(0.0), vec3(0.2, 0.5, 0.8), clamp(v_texcoord.y, 0.0, 1.0)), 1.0);
}
