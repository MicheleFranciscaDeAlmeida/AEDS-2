#include <stdio.h>
#include <stdlib.h>

void CiframentoCesarRecursiva(char *s, int i)
{
    if (s[i] != '\0')
    {
        s[i] = (char)(s[i] + 3);

        CiframentoCesarRecursiva(s, i + 1);
    }
}

int main()
{
    char s[1000];
    // Lê a linha inteira incluindo espaços até encontrar o fim do arquivo
    while (fgets(s, sizeof(s), stdin) != NULL)
    {

        int len = 0;
        while (s[len] != '\0')
        {
            len++;
        }

        if (len > 0 && s[len - 1] == '\n')
        {
            s[len - 1] = '\0';
        }

        if (s[0] == 'F' && s[1] == 'I' &&
            s[2] == 'M' && s[3] == '\0')
        {
            return 0;
        }

        CiframentoCesarRecursiva(s, 0);
        printf("%s\n", s);
    }

    return 0;
}