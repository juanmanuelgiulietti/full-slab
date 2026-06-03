import requests
from bs4 import BeautifulSoup
import pandas as pd
import time

PRODUCTOS_FINAL = []
NOMBRES_VISTOS = set()

HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
}

CATEGORIAS = [
    {"nombre": "Equipos", "id_bd": 1, "url": "https://www.onelab.com.ar/equipos-consumibles", "size": 30},
    {"nombre": "Análisis de agua", "id_bd": 2, "url": "https://www.onelab.com.ar/insumos-analisis-de-agua", "size": 30},
    {"nombre": "Manejo de líquidos", "id_bd": 4, "url": "https://www.onelab.com.ar/manejo-de-liquidos", "size": 30},
    
    {"nombre": "Consumibles", "id_bd": 3, "url": "https://www.onelab.com.ar/consumibles", "size": 30},
    {"nombre": "Material de vidrio", "id_bd": 7, "url": "https://www.onelab.com.ar/vidrios", "size": 30},
    {"nombre": "Material plástico", "id_bd": 5, "url": "https://www.onelab.com.ar/material-de-plastico", "size": 30},
    {"nombre": "Material de porcelana y cuarzo", "id_bd": 6, "url": "https://www.onelab.com.ar/material-de-porcelanacuarzo", "size": 30}
]

print("=======================================================")
print("      INICIANDO EXTRACCIÓN MAESTRA DEFINITIVA          ")
print("=======================================================")

for cat in CATEGORIAS:
    print(f"\n🚀 Extrayendo Categoría: [{cat['nombre']}]")
    pagina = 1
    productos_de_esta_cat = 0
    
    while True:
        url_completa = f"{cat['url']}?pagenumber={pagina}&pagesize={cat['size']}"
        print(f" -> Leyendo página {pagina}...", end="", flush=True)
        
        try:
            res = requests.get(url_completa, headers=HEADERS, timeout=10)
            if res.status_code != 200:
                print(f" [Error {res.status_code}]")
                break
                
            soup = BeautifulSoup(res.text, 'html.parser')
            tarjetas = soup.find_all('div', class_='product-item')
            
            if not tarjetas:
                print(" [Fin de categoría - No hay más productos]")
                break
                
            nuevos_en_pagina = 0
            for tarjeta in tarjetas:
                titulo_tag = tarjeta.find('h2', class_='product-title')
                if not titulo_tag: 
                    continue
                nombre = titulo_tag.get_text().strip()
                
                if nombre not in NOMBRES_VISTOS:
                    NOMBRES_VISTOS.add(nombre)
                    
                    textos = list(tarjeta.stripped_strings)
                    precio = next((t for t in textos if "USD" in t), "A consultar")
                    
                    PRODUCTOS_FINAL.append({
                        "nombre": nombre,
                        "precio_referencia": precio,
                        "categoria_id": cat["id_bd"]
                    })
                    nuevos_en_pagina += 1
                    productos_de_esta_cat += 1
                    
            print(f" [OK - Se agregaron {nuevos_en_pagina} productos]")
            
            if nuevos_en_pagina == 0:
                print(" -> [Corte por control de duplicación masiva]")
                break
                
            pagina += 1
            time.sleep(1.2)
            
        except Exception as e:
            print(f" [Error inesperado: {e}]")
            break
            
    print(f" Total de [{cat['nombre']}]: {productos_de_esta_cat} productos.")

print("\n=======================================================")
if PRODUCTOS_FINAL:
    df = pd.DataFrame(PRODUCTOS_FINAL)
    nombre_excel = "catalogo_completo_onelab.xlsx"
    df.to_excel(nombre_excel, index=False)
    print("                 ¡OPERACIÓN EXITOSA!                   ")
    print("=======================================================")
    print(f" Se descargó TODO el catálogo de One Lab en un solo archivo.")
    print(f" Total de filas guardadas: {len(PRODUCTOS_FINAL)} productos estructurados.")
    print(f" Archivo generado: '{nombre_excel}'")
else:
    print("[!] Alerta: No se pudieron extraer datos.")
print("=======================================================")