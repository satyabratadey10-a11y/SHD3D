#include "Engine.h"
#include <GLES3/gl32.h>
void Engine::init(int w, int h) {
    glViewport(0, 0, w, h);
    glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
}
