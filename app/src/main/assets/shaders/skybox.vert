#version 320 es
in vec3 a_position;
uniform mat4 u_mvp;
out vec3 v_texcoord;
void main() {
    v_texcoord = a_position;
    gl_Position = (u_mvp * vec4(a_position, 1.0)).xyww;
}
