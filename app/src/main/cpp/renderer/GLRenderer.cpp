#include "GLRenderer.h"
#include <GLES3/gl32.h>
void GLRenderer::render() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}
