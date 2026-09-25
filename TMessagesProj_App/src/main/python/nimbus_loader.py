import importlib.util
import sys

_loaded_modules = []

def load_plugin(path, api):
    """Загружает один .nimp файл по полному пути и вызывает on_load(api)"""
    module_name = "nimbus_plugin_" + str(len(_loaded_modules))
    spec = importlib.util.spec_from_file_location(module_name, path)
    module = importlib.util.module_from_spec(spec)
    sys.modules[module_name] = module
    spec.loader.exec_module(module)

    if hasattr(module, "on_load"):
        module.on_load(api)

    _loaded_modules.append(module)
