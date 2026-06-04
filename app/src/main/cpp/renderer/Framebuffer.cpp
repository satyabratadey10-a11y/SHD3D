#include "Framebuffer.h"
#include <GLES3/gl32.h>
void Framebuffer::bind() {
    glBindFramebuffer(GL_FRAMEBUFFER, 0);
}
