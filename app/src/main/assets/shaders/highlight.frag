#version 320 es
precision highp float;
uniform float u_time;
out vec4 fragColor;
void main() {
    float pulse = (sin(u_time * 5.0) + 1.0) * 0.5;
    fragColor = vec4(1.0, 0.8, 0.2, (0.5 + pulse * 0.5));
}
